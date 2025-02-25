package lotto.domain.model

import lotto.domain.service.FixedLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoMachineTest {
    @CsvSource(
        "1, 1",
        "10, 10",
        "14, 14",
    )
    @ParameterizedTest(name = "count가 {0}일 때, {1}개의 로또를 생성할 수 있다.")
    fun `제시한 생성 개수 만큼 로또를 생성할 수 있다`(
        amount: Int,
        count: Int,
    ) {
        val lottoBundle = LottoMachine().generateLottoBundle(amount)

        assertThat(lottoBundle.lottos.size).isEqualTo(count)
    }

    @Test
    fun `생성된 로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(2, 1, 5, 4, 6, 3)
        val actual = listOf(1, 2, 3, 4, 5, 6)

        val numberGenerator = FixedLottoNumbersGenerator(numbers)
        val lotto = LottoMachine(generator = numberGenerator).generateLottoBundle(1).lottos.first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }
}
