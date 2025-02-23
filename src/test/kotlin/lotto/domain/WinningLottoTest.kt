package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private lateinit var winningNumbers1to6: Lotto
    private val bonusNumber45: LottoNumber = LottoNumber(45)

    @BeforeEach
    fun setUp() {
        winningNumbers1to6 = Lotto.createManual((1..6).toSet())
    }

    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            WinningLotto(winningNumbers1to6, bonusNumber45)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers1to6, bonusNumber)
        }
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하면 등수는 2등이다`() {
        // Given
        val lotto = Lotto.createManual(setOf(1, 2, 3, 4, 5, 45))
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)

        // When
        val rank = winningLotto.getRank(lotto)

        // Then
        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하지 않으면 등수는 3등이다`() {
        // Given
        val lotto = Lotto.createManual(setOf(1, 2, 3, 4, 5, 20))
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)

        // When
        val rank = winningLotto.getRank(lotto)

        // Then
        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `일치하는 번호가 2개 이하이면 등수는 MISS이다`() {
        // Given
        val lotto = Lotto.createManual(setOf(1, 2, 23, 24, 25, 26))
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)

        // When
        val rank = winningLotto.getRank(lotto)

        // Then
        assertThat(rank).isEqualTo(Rank.MISS)
    }
}
