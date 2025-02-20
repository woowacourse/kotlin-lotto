package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class WinningLottoTest {
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
