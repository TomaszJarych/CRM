package pl.coderslab.Commons.Service;

import java.io.Serializable;
import java.util.List;

public interface BaseCrudService <D, I extends Serializable>{
	
	D findById(Long id);
	
	D save(D dto);
	
	void deleteFromDb(Long id);
	
	List<D> getAll();

}
