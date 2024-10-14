# frozen_string_literal: true

require_relative 'test_helper'
require_relative '../lib/stack'

class StackTest < Minitest::Test
  # BEGIN
  def setup
    @stack = Stack.new
  end

  def test_push_adds_element
    @stack.push!(1)
    assert { @stack.size == 1 }
    assert { @stack.to_a == [1] }
  end

  def test_pop_removes_element
    @stack.push!(1)
    @stack.push!(2)
    assert { @stack.pop! == 2 }
    assert { @stack.size == 1 }
    assert { @stack.to_a == [1] }
  end

  def test_clear_empties_stack
    @stack.push!(1)
    @stack.push!(2)
    @stack.clear!
    assert { @stack.empty? }
    assert { @stack.size == 0 }
    assert { @stack.to_a == [] }
  end

  def test_empty_checks_stack
    assert { @stack.empty? }
    @stack.push!(1)
    assert { !@stack.empty? }
  end
  # END
end

test_methods = StackTest.new({}).methods.select { |method| method.start_with? 'test_' }
raise 'StackTest has not tests!' if test_methods.empty?
