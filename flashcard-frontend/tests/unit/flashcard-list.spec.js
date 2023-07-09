import { mount } from '@vue/test-utils'
import FlashcardList from '@/components/FlashcardList'
import Flashcard from '@/components/Flashcard'

describe('Testing FlashcardList.vue', () => {
  it('should render a flashcard component for each flashcard', () => {
    // Given
    const flashcards = [
      {
        id: 1,
        question: 'Question 1',
        answer: 'Answer 1',
      },
      {
        id: 2,
        question: 'Question 2',
        answer: 'Answer 2',
      }
    ]

    // When
    const wrapper = mount(FlashcardList, {
      propsData: {
        flashcards: flashcards
      }
    })

    // Then
    const flashcardComponents = wrapper.findAllComponents(Flashcard)
    expect(flashcardComponents.length).toBe(flashcards.length)
  })
})
