package domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `한 장의 Lotto에 대한 당첨 결과 구하기`() {
        val purchaseLotto =
            listOf(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
            )
        val winningLotto =
            WinningLotto(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                        LottoNumber(7),
                    ),
                ),
                2,
            )
        val result = winningLotto.calculate(purchaseLotto).result
        assertThat(result[Rank.SECOND]).isEqualTo(1)
    }

    @Test
    fun `n장의 Lotto에 대한 당첨 결과 구하기`() {
        val purchaseLotto =
            listOf(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
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
            )

        val winningLotto =
            WinningLotto(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                        LottoNumber(7),
                    ),
                ),
                2,
            )
        val result = winningLotto.calculate(purchaseLotto).result

        assertThat(result[Rank.SECOND]).isEqualTo(2)
        assertThat(result[Rank.FOURTH]).isEqualTo(0)
        assertThat(result[Rank.MISS]).isEqualTo(0)
    }

    @Test
    fun `로또 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        val lotto =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                    LottoNumber(7),
                ),
            )

        assertThrows<IllegalArgumentException> {
            WinningLotto(lotto, 6)
        }.apply { assertThat(this).hasMessage("[ERROR] 보너스 번호와 로또 번호는 중복될 수 없습니다.") }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `보너스볼 번호는 1이상 45 이하여야한다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                        LottoNumber(7),
                    ),
                ),
                value,
            )
        }.apply { assertThat(this).hasMessage("[ERROR] 로또 번호는 1부터 45 사이입니다.") }
    }
}
