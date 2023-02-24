package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoFactoryTest {

    @Test
    fun `입력된 개수만큼 로또를 생성할 수 있다`() {
        val count = Count(10)
        val result = LottoFactory.create(count)

        assertThat(result).hasSize(count.value)
    }
}
