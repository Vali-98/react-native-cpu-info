import { Text, View, StyleSheet } from 'react-native';
import { getThreads, getCpuFeatures } from '@vali98/react-native-cpu-info';
import { useEffect, useState } from 'react';

const App = () => {
  const [feats, setFeats] = useState({});
  useEffect(() => {
    getCpuFeatures().then(setFeats);
  }, []);

  return (
    <View style={styles.container}>
      <Text>Thread Count: {getThreads()}</Text>
      <Text>{JSON.stringify(feats)}</Text>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
