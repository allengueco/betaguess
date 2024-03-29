package org.allengueco.repository;

import org.allengueco.game.GameSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameSession, String> {
}
