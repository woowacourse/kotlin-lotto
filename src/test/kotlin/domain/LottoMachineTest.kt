package domain

import model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `generator가 (1,2,3,4,5,6)인 리스트를 생성했을 때 로또 번호가(1,2,3,4,5,6)인 로또가 생성된다`() {
        // when
        val actual = LottoMachine(TestLottoNumbersGenerator()).generateLotto().lottoNumbers
        // then
        val expected = listOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
        )

        assertThat(actual).isEqualTo(expected)
    }

    class TestLottoNumbersGenerator() : NumbersGenerator {
        override fun generate(): List<Int> {
            return listOf(1, 2, 3, 4, 5, 6)
        }
    }
}
