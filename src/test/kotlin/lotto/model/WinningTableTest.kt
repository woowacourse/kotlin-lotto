package lotto.model

import lotto.model.Rank.FIFTH
import lotto.model.Rank.FIRST
import lotto.model.Rank.FOURTH
import lotto.model.Rank.SECOND
import lotto.model.Rank.SIXTH
import lotto.model.Rank.THIRD
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class WinningTableTest {
    @Test
    fun `RankMap의 value에 null이 없다`() {
        val ranks =
            mapOf(
                FIRST to Count(0),
                SECOND to Count(0),
                THIRD to Count(1),
                FOURTH to Count(1),
                FIFTH to Count(0),
                SIXTH to Count(0),
            )
        assertDoesNotThrow {
            WinningTable(ranks)
        }
    }
}
