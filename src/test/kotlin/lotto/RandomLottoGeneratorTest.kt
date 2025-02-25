package lotto

import lotto.model.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {
    @Test
    fun `로또를 요청한 개수만큼 생성한다`() {
        val generator = RandomLottoGenerator()
        val count = 5

        val lottos = generator.generate(count)

        assertThat(lottos).hasSize(count)
    }
}
