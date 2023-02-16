package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinningLottoTest {
    @Test
    fun `로또 번호, 로또 번호와 중복되지 않는 보너스 번호로 이루어진 당첨 번호를 생성할 수 있다`() {
        val winningLotto = WinningLotto(intArrayOf(1, 2, 3, 4, 5, 6), 10)
        assertAll(
            { assertEquals(winningLotto.lotto, Lotto(1, 2, 3, 4, 5, 6)) },
            { assertEquals(winningLotto.bonusNumber, LottoNumber(10)) },
        )
    }

    @Test
    fun `보너스 번호가 로또 번호와 중복되면 에러가 발생한다`() {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 3
        assertThatIllegalArgumentException()
            .isThrownBy { WinningLotto(numbers, bonusNumber) }
            .withMessage(
                "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : ${
                    Lotto(
                        numbers.map(::LottoNumber).toSet(),
                    )
                }, ${LottoNumber(bonusNumber)}",
            )
    }

    @Test
    fun `로또 결과를 정상적으로 저장할 수 있다`() {
        val lottos = listOf(
            Lotto(1, 2, 3, 4, 5, 6), // 1등
            Lotto(2, 3, 4, 5, 6, 7), // 2등
            Lotto(3, 4, 5, 6, 7, 8), // 4등
        )
        val winningLotto = WinningLotto(intArrayOf(1, 2, 3, 4, 5, 6), 7)
        val result = winningLotto.match(lottos)
        val expect = LottoResult(
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            ),
        )
        assertEquals(result, expect)
    }
}
