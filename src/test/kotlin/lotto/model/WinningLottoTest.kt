package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningLottoTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber("7".toIntOrNull() ?: 0)
        winningLotto = WinningLotto(winningNumbers.map { LottoNumber(it) }, bonusNumber)
    }

    @Test
    fun `로또에 대한 등수 판단 확인 테스트, (보너스 번호가 맞았을 때)`() {
        val publishedLotto =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )

        val actual = Rank.SECOND
        val excepted = winningLotto.judgeRank(publishedLotto)

        assertThat(actual).isEqualTo(excepted)
    }

    @Test
    fun `로또에 대한 등수 판단 확인 테스트, (보너스 번호가 맞지 않았을 때)`() {
        val publishedLotto =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(8),
                ),
            )

        val actual = Rank.THIRD
        val excepted = winningLotto.judgeRank(publishedLotto)

        assertThat(actual).isEqualTo(excepted)
    }
}
