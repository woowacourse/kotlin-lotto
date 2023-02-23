package lotto.model.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottosGeneratorTest {

    @Test
    fun `count 수 만큼의 로또를 발행한다`() {
        assertThat(
            RandomLottosGenerator().generate(count = 3).size
        ).isEqualTo(3)
    }

    @Test
    fun `발행한 로또의 숫자 개수가 6개이다`() {
        assertThat(
            RandomLottosGenerator().generate(1)[0].lotto.size
        ).isEqualTo(6)
    }
}
