package co.takan.server.controller

import co.takan.server.data.api.GameBoard
import co.takan.server.data.game.repo.RoadRepository
import co.takan.server.data.game.repo.StructureRepository
import co.takan.server.data.game.repo.TileRepository
import co.takan.server.restartGame
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


private val logger by lazy { LoggerFactory.getLogger("GameController") }

@RestController
class GameController {

  @Autowired lateinit var tileRepo: TileRepository
  @Autowired lateinit var roadRepo: RoadRepository
  @Autowired lateinit var structureRepo: StructureRepository

  @GetMapping("/hello")
  fun hello(name: String = "Lenrod Cornboy"): String {
    return "Hello, $name!"
  }

  @GetMapping("/board")
  fun getGameBoard(): GameBoard {
    logger.info("getGameBoard()")
    val tiles = tileRepo.findAll().toList()
    val tileMatrix = listOf(
        tiles.subList(0, 3),
        tiles.subList(3, 7),
        tiles.subList(7, 12),
        tiles.subList(12, 16),
        tiles.subList(16, 19)
    )
    return GameBoard(
        tiles = tileMatrix,
        roads = roadRepo.findAll().toList(),
        structures = structureRepo.findAll().toList()
    )
  }

  @PostMapping("/start")
  fun startGame() {
    logger.info("startGame()")
    restartGame(tileRepo, roadRepo, structureRepo)
  }

}