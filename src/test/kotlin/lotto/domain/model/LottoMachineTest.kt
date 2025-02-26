package lotto.domain.model

import lotto.domain.service.ManualLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

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

    @RepeatedTest(5)
    fun `생성된 로또 번호는 정렬되어야 한다`() {
        val lotto = LottoMachine().generateLottoBundle(1).lottos.first()
        val actual = lotto.numbers.map { it.number }
        assertThat(actual).isSorted
    }

    @MethodSource("manualLottoGeneratorTest")
    @ParameterizedTest(name = "입력한 수동 번호가 {0}일 때, 생성된 로또는 {1}를 가져야 한다.")
    fun `원하는 로또 번호로 로또를 생성할 있다`(
        numbers: List<Int>,
        expected: List<Int>,
    ) {
        val generator = ManualLottoNumbersGenerator(listOf(numbers))
        val lottoMachine = LottoMachine(generator)

        val lotto = lottoMachine.generateLottoBundle(1).lottos.first()
        val actual = lotto.numbers.map { it.number }

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun manualLottoGeneratorTest(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 4, 5, 6)),
                Arguments.arguments(listOf(4, 6, 7, 9, 19, 24), listOf(4, 6, 7, 9, 19, 24)),
                Arguments.arguments(listOf(7, 9, 6, 18, 24, 42), listOf(6, 7, 9, 18, 24, 42)),
                Arguments.arguments(listOf(9, 15, 19, 29, 43, 1), listOf(1, 9, 15, 19, 29, 43)),
            )
        }
    }
}
