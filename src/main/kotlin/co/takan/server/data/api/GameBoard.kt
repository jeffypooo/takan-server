package co.takan.server.data.api

import co.takan.server.data.game.model.Road
import co.takan.server.data.game.model.Structure
import co.takan.server.data.game.model.Tile

data class GameBoard(
    val tiles: List<List<Tile>>,
    val roads: List<Road>,
    val structures: List<Structure>
)