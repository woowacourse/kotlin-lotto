package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `수동으로 Int 컬렉션을 받아 6개의 로또 번호 Set을 갖는 로또 인스턴스를 만든다`() {
        // given
        val oneToSixLottoNumberList = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }
        val oneToSixLottoNumberSet = setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()

        // when
        val lottoMadeByList = Lotto.createSelfByManualLottoNumbers(oneToSixLottoNumberList)
        val lottoMadeBySet = Lotto.createSelfByManualLottoNumbers(oneToSixLottoNumberSet)

        // then
        assertThat(lottoMadeByList.lottoNumbers.size).isEqualTo(6)
        assertThat(lottoMadeBySet.lottoNumbers.size).isEqualTo(6)
    }

    @Test
    fun `자동으로 6개의 로또 번호 Set을 갖는 로또 인스턴스를 만든다`() {
        // given when
        val lottoMadeByRandom = Lotto.createSelfRandomly()

        // then
        assertThat(lottoMadeByRandom.lottoNumbers.size).isEqualTo(6)
    }

    @Test
    fun `수동으로 생성된 로또 인스턴스의 번호들을 오름차순으로 정렬하여 반환한다`() {
        // Given
        val lotto = Lotto.createSelfByManualLottoNumbers(listOf(4, 5, 6, 1, 2, 3).map { LottoNumber(it) })

        // When
        val lottoNumbers = lotto.getSortedLottoNumbers()

        // Then
        assertThat(lottoNumbers.map { it.number }).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun `자동으로 생성된 로또 인스턴스의 번호들을 오름차순으로 정렬하어 반환한다`() {
        // Given
        val lotto = Lotto.createSelfRandomly()
        val sortedRandomLottoNumbers = lotto.lottoNumbers.toList().sortedBy { it.number }

        // When
        val lottoNumbers = lotto.getSortedLottoNumbers()

        // Then
        assertThat(lottoNumbers).isEqualTo(sortedRandomLottoNumbers)
    }
}
