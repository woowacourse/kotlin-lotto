package domain.model

import domain.fixture.createLotto
import domain.fixture.createWinningLotto
import domain.model.number.LottoNumber
import domain.model.number.LottoNumberException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `n장의 Lotto에 대한 당첨 결과 구하기`() {
        val lottos =
            listOf(
                createLotto(1, 2, 3, 4, 5, 6),
                createLotto(2, 3, 4, 5, 6, 7),
                createLotto(3, 4, 5, 6, 7, 8),
            )

        val winningLotto = createWinningLotto(1, 3, 4, 5, 6, 7, bonus = 2)
        val result = winningLotto.calculate(lottos)

        assertThat(result.getWinningCount(Rank.SECOND)).isEqualTo(2)
        assertThat(result.getWinningCount(Rank.FOURTH)).isEqualTo(0)
        assertThat(result.getWinningCount(Rank.MISS)).isEqualTo(0)
    }

    @Test
    fun `로또 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        val lotto = createLotto(11, 22, 33, 44, 45, 7)

        assertThrows<IllegalArgumentException>(
            message = "[ERROR] 보너스 번호와 로또 번호는 중복될 수 없습니다.",
        ) {
            WinningLotto(lotto, LottoNumber(7))
        }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `보너스볼 번호는 1이상 45 이하여야한다`(value: Int) {
        assertThrows<LottoNumberException.InvalidLottoNumberRange>(
            message = "[ERROR] 로또 번호는 1부터 45 사이입니다.",
        ) {
            createWinningLotto(10, 20, 30, 40, 44, 45, bonus = value)
        }
    }
}
