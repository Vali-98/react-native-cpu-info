import { Text, View, StyleSheet } from 'react-native';
import { getThreads } from '@vali98/react-native-cpu-info';

const App = () => {
  return (
    <View style={styles.container}>
      <Text>Thread Count: {getThreads()}</Text>
    </View>
  );
}

export default App

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});