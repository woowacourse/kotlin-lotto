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
                FIRST to 0,
                SECOND to 0,
                THIRD to 1,
                FOURTH to 1,
                FIFTH to 0,
                SIXTH to 0,
            )
        assertDoesNotThrow {
            WinningTable(ranks)
        }
    }
}
