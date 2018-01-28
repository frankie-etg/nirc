package org.etg.nirc.net;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

/**
 * PipelineFactory for IRC protocol
 * @author Mitchell Bolen
 */
public class PipelineFactory implements ChannelPipelineFactory {

	/** The maximum length of an IRC message */
	private static final int MAX_FRAME_LENGTH = 512;
	
	/**
	 * Creates a new pipeline with the proper codecs
	 * @throws Exception
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(MAX_FRAME_LENGTH, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder(CharsetUtil.US_ASCII));
		pipeline.addLast("encoder", new StringEncoder(CharsetUtil.US_ASCII));
		pipeline.addLast("handler", new ConnectionHandler());
		return pipeline;
	}

}
