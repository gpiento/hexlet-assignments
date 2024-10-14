# frozen_string_literal: true

# BEGIN
module Model
  def self.included(base)
    base.extend(ClassMethods)
  end

  module ClassMethods
    def attribute(name, options = {})
      define_method(name) do
        value = instance_variable_get("@#{name}")
        return value if value.nil? || !options[:type]

        convert_value(value, options[:type])
      end

      define_method("#{name}=") do |value|
        instance_variable_set("@#{name}", value)
      end

      @attributes ||= {}
      @attributes[name] = options
    end

    def attributes
      @attributes || {}
    end
  end

  def initialize(attributes = {})
    self.class.attributes.each do |name, options|
      value = attributes.key?(name) ? attributes[name] : options[:default]
      instance_variable_set("@#{name}", value)
    end
  end

  def attributes
    self.class.attributes.keys.each_with_object({}) do |name, result|
      result[name] = send(name)
    end
  end

  private

  def convert_value(value, type)
    case type
    when :string
      value.to_s
    when :integer
      value.to_i
    when :boolean
      !!value
    when :datetime
      DateTime.parse(value)
    else
      value
    end
  end
end
# END
