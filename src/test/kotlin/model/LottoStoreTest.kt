package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import utils.ExplicitLottoGenerationStrategy
import utils.RandomLottoGenerationStrategy

class LottoStoreTest {
    @ParameterizedTest
    @CsvSource("5000, 5", "10000, 10", "1000, 1", "3500, 3", "15500, 15")
    fun `로또가 구매한 갯수만큼 발행에 성공`(
        input: String,
        count: Int,
    ) {
        LottoStore.setStrategy(RandomLottoGenerationStrategy(Amount(input)))
        assertThat(LottoStore.makeLotto().size).isEqualTo(count)
    }

    @Test
    fun `전략 패턴을 이용하여 로또 값을 지정해서 테스트`() {
        val lottos =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            ).map { Lotto.fromList(it) }
        LottoStore.setStrategy(ExplicitLottoGenerationStrategy(lottos))

        val userLottos = LottoStore.makeLotto()
        userLottos.forEachIndexed { index, userLotto ->
            assertThat(userLotto.getCountOfMatch(lottos[index])).isEqualTo(6)
        }
    }
}
