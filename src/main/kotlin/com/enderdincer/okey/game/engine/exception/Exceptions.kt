package com.enderdincer.okey.game.engine.exception

class InvalidGameConfigException(override val message: String) : RuntimeException(message)

class NoPlayerFoundException(override val message: String) : RuntimeException(message)

class UnknownGameActionException(override val message: String) : RuntimeException(message)

class GameEventValidationException(override val message: String) : RuntimeException(message)
