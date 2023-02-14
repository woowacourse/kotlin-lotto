package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoGeneratorTest {
    @Test
    fun `구입 금액이 3000원이고, 로또 가격이 1000원일 때, 로또를 3개 생성한다`() {
        assertThat(LottoGenerator(3000, 1000, SequentialLottoNumberGenerator(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18))).lottos.size).isEqualTo(3)
    }
}
