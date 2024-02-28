package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningLottoTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val winningNumbers =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val bonusNumber = LottoNumber(7)
        winningLotto =
            WinningLotto(
                Lotto(
                    winningNumbers,
                ),
                bonusNumber,
            )
    }

    @Test
    fun `로또 번호 당첨 개수 일치 확인`() {
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val actual = winningLotto.calculateCountOfMatch(lotto)
        val expected = 6

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 번호 당첨 개수 불일치 확인`() {
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val actual = winningLotto.calculateCountOfMatch(lotto)
        val expected = 3

        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun `보너스 번호 일치 확인`() {
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )

        val actual = winningLotto.checkBonusNumberMatched(lotto)
        val expected = true

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호 불일치 확인`() {
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val actual = winningLotto.checkBonusNumberMatched(lotto)
        val expected = false

        assertThat(actual).isEqualTo(expected)
    }
}
