package soares.oliveira.thamyris.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBaseService<BaseEntity> {

    public BaseEntity insert(BaseEntity obj);
    public BaseEntity update(BaseEntity obj);
    public List<BaseEntity> findAll();
    public Optional<BaseEntity> findById(long id);
}
