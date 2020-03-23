package co.takan.server

import co.takan.server.data.game.repo.TileRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TileRepositoryTest @Autowired constructor(
    val repo: TileRepository
) {

  @Test
  fun `find all works`() {
    repo.findAll();
  }

}