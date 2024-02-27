package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DefaultLottoMachineTest {
    @ParameterizedTest
    @CsvSource(value = ["1:1000:1000:true", "1:1001:1000:false", "1:1000:1001:true"], delimiter = ':')
    fun `lottoPrice * count 가 지불 cost 이상이면 count 만큼 Lotto 를 생성할 수 있다`(
        lottoCount: Int,
        lottoPrice: Int,
        cost: Int,
        canGenerate: Boolean,
    ) {
        // given
        val lottoMachine =
            DefaultLottoMachine(
                lottoPrice = Money(lottoPrice),
                autoLottieGenerator = { listOf() },
                manualLottieGenerator = { listOf() },
            )
        // when
        val actual = lottoMachine.canGenerate(LottoCount(lottoCount), Money(cost))
        // then
        assertThat(actual).isEqualTo(canGenerate)
    }
}
