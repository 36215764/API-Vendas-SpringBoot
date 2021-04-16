package io.richsoftwares.vendasjpa.domain.repository;

import io.richsoftwares.vendasjpa.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
