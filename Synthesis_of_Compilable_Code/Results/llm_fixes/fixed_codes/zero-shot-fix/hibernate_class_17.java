package hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.shards.api.ShardConfiguration;
import org.hibernate.shards.api.ShardStrategy;
import org.hibernate.shards.api.ShardStrategyFactory;
import org.hibernate.shards.loadbalance.RoundRobinShardLoadBalancer;
import org.hibernate.shards.strategy.access.SequentialShardAccessStrategy;
import org.hibernate.shards.strategy.resolution.AllShardsShardResolutionStrategy;
import org.hibernate.shards.strategy.selection.RoundRobinShardSelectionStrategy;
import org.hibernate.shards.strategy.ShardedConfiguration;
import org.hibernate.shards.util.ConfigurationToShardConfigurationAdapter;

import javax.naming.NamingException;

public class hibernate_class_17 {
    public class SessionFactoryImpl {

        private static final String SHARD_CFG_0 = "/com/hibshards/config/shard0.hibernate.cfg.xml";
        private static final String SHARD_CFG_1 = "/com/hibshards/config/shard1.hibernate.cfg.xml";
        private static final String SHARDED_TABLE = "com/hibshards/orm/weather.hbm.xml";

        public SessionFactory createSessionFactory() throws NamingException {
            Configuration prototypeConfig = new Configuration().configure( SHARD_CFG_0 );
            prototypeConfig.addResource( SHARDED_TABLE );

            List<ShardConfiguration> shardConfigs = new ArrayList<ShardConfiguration>();
            shardConfigs.add( buildShardConfig( SHARD_CFG_0 ) );
            shardConfigs.add( buildShardConfig( SHARD_CFG_1 ) );

            ShardStrategyFactory shardStrategyFactory = buildShardStrategyFactory();
            ShardedConfiguration shardedConfig = new ShardedConfiguration(
                    prototypeConfig,
                    shardConfigs,
                    shardStrategyFactory);
            return shardedConfig.buildShardedSessionFactory();
        }

        private ShardStrategyFactory buildShardStrategyFactory() {

            ShardStrategyFactory shardStrategyFactory = new ShardStrategyFactory() {
                public ShardStrategy newShardStrategy1(List shardIds) {
                    RoundRobinShardLoadBalancer loadBalancer = new RoundRobinShardLoadBalancer( shardIds );
                    ShardSelectionStrategy pss = new RoundRobinShardSelectionStrategy( loadBalancer );
                    ShardResolutionStrategy prs = new AllShardsShardResolutionStrategy( shardIds );
                    ShardAccessStrategy pas = new SequentialShardAccessStrategy();
                    return new ShardStrategyImpl( pss, prs, pas );
                }

                @Override
                public ShardStrategy newShardStrategy(List arg0) {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
            return shardStrategyFactory;
        }

        private ShardConfiguration buildShardConfig( String configFile ) {
            Configuration config = new Configuration().configure( configFile );
            return new ConfigurationToShardConfigurationAdapter( config );
        }
    }
}