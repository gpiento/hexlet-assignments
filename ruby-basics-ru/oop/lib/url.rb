# frozen_string_literal: true

# BEGIN
require 'uri'
require 'forwardable'

class Url
  include Comparable
  extend Forwardable

  def_delegators :@uri, :scheme, :host, :port

  def initialize(url)
    @uri = URI.parse(url)
    @query_params = parse_query_params(@uri.query)
  end

  def query_params
    @query_params
  end

  def query_param(key, default = nil)
    @query_params.fetch(key, default)
  end

  def <=>(other)
    return unless other.is_a?(Url)

    [scheme, host, port, sorted_query_params] <=> [other.scheme, other.host, other.port, other.sorted_query_params]
  end

  protected

  def sorted_query_params
    query_params.sort.to_h
  end

  private

  def parse_query_params(query)
    return {} if query.nil?

    query.split('&').each_with_object({}) do |param, params|
      key, value = param.split('=')
      params[key.to_sym] = value
    end
  end
end
# END
