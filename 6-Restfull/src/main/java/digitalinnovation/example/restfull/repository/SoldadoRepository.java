package digitalinnovation.example.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digitalinnovation.example.restfull.entity.SoldadoEntity;

@Repository
public interface SoldadoRepository extends JpaRepository<SoldadoEntity, Long> {

}
