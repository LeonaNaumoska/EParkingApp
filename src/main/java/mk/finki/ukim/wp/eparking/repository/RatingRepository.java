package mk.finki.ukim.wp.eparking.repository;

import mk.finki.ukim.wp.eparking.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
