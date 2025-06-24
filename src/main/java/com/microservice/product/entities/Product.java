package com.microservice.product.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un producto en el sistema")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del producto generado automáticamente", example = "1")
    private long id;

    @Schema(description = "Nombre del producto", example = "Smartphone X", required = true)
    private String name;

    @Schema(description = "Descripción detallada del producto",
            example = "Último modelo de smartphone con pantalla AMOLED de 6.5 pulgadas")
    private String description;

    @Schema(description = "Precio del producto en la moneda local", example = "999.99")
    private double price;

    @Schema(description = "Categoría principal del producto", example = "Electrónicos")
    private String category;

    @Schema(description = "Subcategoría del producto", example = "Teléfonos móviles")
    private String subcategory;
}