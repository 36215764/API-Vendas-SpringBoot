package io.richsoftwares.vendasjpa.service;


import io.richsoftwares.vendasjpa.domain.entity.Pedido;
import io.richsoftwares.vendasjpa.domain.enums.StatusPedido;
import io.richsoftwares.vendasjpa.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
