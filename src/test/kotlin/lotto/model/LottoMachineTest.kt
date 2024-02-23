package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    val fixedNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 999])
    fun `구입 금액은 자연수이면서 1000 이상이다`(price: Int) {
        assertThrows<IllegalArgumentException> {
            LottoMachine(price, LottoFixedNumberGenerator(fixedNumbers))
        }
    }

    @Test
    fun `사전에 정의된 범위로 로또를 생성한다`() {
        val lottoMachine = LottoMachine(Lotto.LOTTO_PRICE, LottoFixedNumberGenerator(fixedNumbers))
        val lotto = lottoMachine.createLottos().first()
        val expectedLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        assertThat(lotto).isEqualTo(expectedLotto)
    }
}
