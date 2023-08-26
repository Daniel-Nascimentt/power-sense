package br.com.power.sense.model;

import br.com.power.sense.model.enums.EstadoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ENDERECO")
public class EnderecoModel {

        @NotNull
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
        private EstadoEnum estado;
        
		public EnderecoModel(@NotBlank String rua, @NotNull Long numero, @NotBlank String bairro, @NotBlank String cidade, @NotNull EstadoEnum estado) {
			this.rua = rua;
			this.numero = numero;
			this.bairro = bairro;
			this.cidade = cidade;
			this.estado = estado;
		}
        
        

}
