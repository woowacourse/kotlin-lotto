package lotto

import lotto.model.generator.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {
    @Test
    fun `자동 로또를 5개 발행한다`() {
        val purchaseCount = 5
        assertThat(RandomLottoGenerator().generate(purchaseCount).size).isEqualTo(5)
    }
}
