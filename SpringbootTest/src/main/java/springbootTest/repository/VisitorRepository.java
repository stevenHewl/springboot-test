package springbootTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootTest.entity.Visitor;

public interface  VisitorRepository extends JpaRepository<Visitor, Long> {
    Visitor findByIp(String ip);
}