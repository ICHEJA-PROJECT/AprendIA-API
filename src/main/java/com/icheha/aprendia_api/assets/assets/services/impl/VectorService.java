package com.icheha.aprendia_api.assets.assets.services.impl;

import com.icheha.aprendia_api.assets.assets.services.IVectorService;
import org.springframework.stereotype.Service;

import ai.djl.inference.Predictor;
import ai.djl.huggingface.translator.TextEmbeddingTranslatorFactory;
import ai.djl.inference.Predictor;
import ai.djl.modality.nlp.embedding.TextEmbedding;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class VectorService implements IVectorService {

    private Predictor<String, float[]> predictor;
    private ZooModel<String, float[]> model;

    @PostConstruct
    public void init() {
        System.out.println("Cargando modelo de embeddings all-MiniLM-L6-v2...");
        System.out.println("(La primera vez puede tardar mientras descarga los pesos y el tokenizer)");

        try {
            // Criterios: usamos el modelo publicado en Hugging Face a través del repositorio DJL
            Criteria<String, float[]> criteria = Criteria.builder()
                    .setTypes(String.class, float[].class)
                    // DJL conoce el esquema djl:// para HuggingFace PyTorch
                    .optModelUrls("djl://ai.djl.huggingface.pytorch/sentence-transformers/all-MiniLM-L6-v2")
                    .optEngine("PyTorch") // usa PyTorch engine (nativo).
                    .optTranslatorFactory(new TextEmbeddingTranslatorFactory())
                    .optProgress(new ProgressBar()) // opcional: ver progreso de descarga
                    .build();

            // Carga el modelo (descarga pesos/tokenizer si es necesario)
            this.model = ModelZoo.loadModel(criteria);
            this.predictor = model.newPredictor();

            System.out.println("Modelo de embeddings cargado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error fatal al cargar el modelo de embeddings: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar el VectorService", e);
        }
    }

    @Override
    public float[] generateVector(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new float[0];
        }
        try {
            return this.predictor.predict(text);
        } catch (Exception e) {
            System.err.println("Error al generar el vector: " + e.getMessage());
            throw new RuntimeException("Error durante la predicción del embedding", e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (this.predictor != null) {
                this.predictor.close();
            }
        } catch (Exception ignored) {}
        if (this.model != null) {
            this.model.close();
            System.out.println("Modelo de embeddings cerrado.");
        }
    }
}
