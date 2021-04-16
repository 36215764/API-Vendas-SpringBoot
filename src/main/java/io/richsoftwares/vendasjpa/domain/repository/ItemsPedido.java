package io.richsoftwares.vendasjpa.domain.repository;

import io.richsoftwares.vendasjpa.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
