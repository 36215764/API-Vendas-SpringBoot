package io.richsoftwares.vendasjpa.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String cpf;
    private String dataPedido;
    private String nomeCliente;
    private BigDecimal total;
    private String status;
    private List<InformacaoItemPedidoDTO> items;
}
