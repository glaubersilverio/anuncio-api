package br.com.divulgatudo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anuncio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anuncio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@NotNull
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@NotNull
	@Column(name = "data_termino")
	private LocalDate dataTermino;
	
	@NotNull
	@Column(name = "investimento_por_dia")
	private BigDecimal investimentoPorDia;
	
}