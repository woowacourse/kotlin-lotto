package lotto.domain

import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestLottoGenerator : LottoGenerator {
    override fun manipulate(numbers: List<Int>) = numbers
}

val testLottoGenerator = TestLottoGenerator()

class LottoGeneratorTest {
    @Test
    fun `로또 숫자 개수가 6개인지 확인한다`() {
        val actual = testLottoGenerator.make()
        assertThat(actual.numbers.size).isEqualTo(6)
    }

    @Test
    fun `결과 로또가 1부터 6까지 오름차 순으로 되어있는지 확인한다`() {
        val actual = testLottoGenerator.make()
        assertThat(actual.numbers).isEqualTo(
            (1..6).map { LottoNumber(it) }
        )
    }
}
