package com.icheha.aprendia_api.assets.assets.data.repositories;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDB;
import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
    @Query(value = """
    SELECT
        a.id_activo AS id,
        a.name AS name,
        a.descripcion AS description,
        a.url AS url,
        ARRAY_AGG(t.nombre) AS tags
    FROM
        activo AS a
    INNER JOIN
        tags_asociadas AS ta ON a.id_activo = ta.id_activo
    INNER JOIN
        tag AS t ON ta.id_tag = t.id_tag
    WHERE
        a.id_activo IN (
            SELECT DISTINCT ta_sub.id_activo
            FROM tags_asociadas AS ta_sub
            WHERE ta_sub.id_tag IN (:tagsIds)
        )
    GROUP BY
        a.id_activo, a.name, a.descripcion, a.url
    LIMIT 100;
    """, nativeQuery = true)
    List<FindAssetDB> getAssetByTags(@Param("tagsIds") List<Long> tagsIds);

    @Query(value = """
    SELECT 
        a.id_activo AS id,
        a.name AS name,
        a.descripcion AS description,
        a.url AS url,
        ARRAY_AGG(t.nombre) AS tags
    FROM
        activo AS a
    INNER JOIN
        tags_asociadas AS ta ON a.id_activo = ta.id_activo
    INNER JOIN
        tag AS t ON ta.id_tag = t.id_tag
    WHERE 
        (a.vector <-> CAST (:description AS vector)) < 0.5
    GROUP BY
        a.id_activo, a.name, a.descripcion, a.url
    ORDER BY
        (a.vector <-> CAST (:description AS vector)) ASC
    LIMIT 100;
    """, nativeQuery = true)
    List<FindAssetDB> getAssetByDescription(@Param("description") float[] description);

}