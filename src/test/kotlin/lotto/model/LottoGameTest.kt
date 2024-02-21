package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoGameTest {
    @Test
    fun `5개의 당첨 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이고, 일치하면 2등이다`() {
        val numbers =
            listOf(
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "6")),
                Lotto(numbers = listOf("1", "2", "3", "4", "5", "8")),
            )
        val winningNumbers = Lotto(numbers = listOf("1", "2", "3", "4", "5", "7"))
        val lottoGame = LottoGame(numbers, winningNumbers, "8")
        val result = lottoGame.calculateResult()

        assertThat(result).isEqualTo(mapOf(Rank.THIRD to 1, Rank.SECOND to 1))
    }
}
