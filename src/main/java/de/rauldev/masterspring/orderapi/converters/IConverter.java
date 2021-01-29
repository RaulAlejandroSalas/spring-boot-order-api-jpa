/**
 * 
 */
package de.rauldev.masterspring.orderapi.converters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author raul
 *
 */
public interface IConverter<E,D> {

	D fromEntity(E entity);
	E fromDTO(D dto);
	default List<D> fromEntity(List<E> entities){
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
	default List<E> fromDTO(List<D> dtos){
		return dtos.stream().map(this::fromDTO).collect(Collectors.toList());
	}
}
