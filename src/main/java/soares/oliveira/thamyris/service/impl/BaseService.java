package soares.oliveira.thamyris.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import soares.oliveira.thamyris.service.IBaseService;

import java.util.List;
import java.util.Optional;

public class BaseService<BaseEntity> implements IBaseService<BaseEntity> {

    private JpaRepository jpaRepository;

    public BaseService(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public BaseEntity insert(BaseEntity obj) {
        return (BaseEntity) jpaRepository.save(obj);
    }

    @Override
    public BaseEntity update(BaseEntity obj) {
        return insert(obj);
    }

    @Override
    public List<BaseEntity> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<BaseEntity> findById(long id) {
        return jpaRepository.findById(id);
    }
}
