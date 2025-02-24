package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ManualLottoCountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -1000])
    fun `수동으로 구매할 로또 수로 0 미만의 숫자가 들어오면 IllegalArgumentException이 발생한다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            ManualLottoCount(input, 100)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 6, 7, 8])
    fun `구입할 수 있는 로또의 개수보다 높은 숫자가 입력된 경우 IllegalArgumentException이 발생한다`(input: Int) {
        assertThrows<IllegalArgumentException> {
            ManualLottoCount(input, 1)
        }
    }
}
