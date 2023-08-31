package br.com.power.sense.model;

import br.com.power.sense.model.enums.EstadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ENDERECO")
@NoArgsConstructor
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String rua;
    @NotNull
    private Long numero;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;
    @NotBlank
    private String cep;
    @ManyToOne
    private ContratanteModel contratante;
    @OneToOne
    private ResidenteModel residente;

    
    public EnderecoModel(@NotBlank String rua, @NotNull Long numero, @NotBlank String bairro, @NotBlank String cidade, @NotNull EstadoEnum estado, String cep, ContratanteModel contratante) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.contratante = contratante;
    }
    
    public EnderecoModel(@NotBlank String rua, @NotNull Long numero, @NotBlank String bairro, @NotBlank String cidade, @NotNull EstadoEnum estado, String cep, ContratanteModel contratante, ResidenteModel residente) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.contratante = contratante;
        this.residente = residente;
    }


}
