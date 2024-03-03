package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoMachineTest {
    @ParameterizedTest
    @MethodSource("로또 발행에 대한 테스트 데이터")
    fun `로또의 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`(
        lottoMachine: LottoMachine,
        lottoSize: Int,
    ) {
        val lottos = lottoMachine.generate(lottoSize)
        assertThat(lottos).allMatch { lotto ->
            lottoNumbers.all { LottoNumber.from(it) in lotto }
        }
    }

    @ParameterizedTest
    @MethodSource("로또 발행에 대한 테스트 데이터")
    fun `지정해준 로또의 개수만큼 로또가 발행된다`(
        lottoMachine: LottoMachine,
        lottoSize: Int,
    ) {
        val actual = lottoMachine.generate(lottoSize).size
        assertThat(actual).isEqualTo(lottoSize)
    }

    companion object {
        private val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)

        @JvmStatic
        fun `로또 발행에 대한 테스트 데이터`() =
            listOf(
                Arguments.of(ManualLottoMachine(listOf(lottoNumbers, lottoNumbers)), 2),
                Arguments.of(LottoMachine { lottoSize -> List(lottoSize) { Lotto.create(lottoNumbers) } }, 5),
            )
    }
}
