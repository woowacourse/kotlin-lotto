package lotto.domain

import lotto.domain.Lotto.Companion.LOTTO_SIZE
import lotto.domain.factory.RandomLottoFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoFactoryTest {
    @Test
    fun `로또 번호는 6개이다`() {
        assertThat(RandomLottoFactory().createLotto().lottoNumbers.size).isEqualTo(LOTTO_SIZE)
    }

    @Test
    fun `로또 숫자는 서로 중복되지 않는다`() {
        assertThat(RandomLottoFactory().createLotto().lottoNumbers.distinctBy { it.value }.size).isEqualTo(LOTTO_SIZE)
    }
}
